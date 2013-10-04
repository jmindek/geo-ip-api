(ns geo-ip-api.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defn parse-int [s]
  (Integer. (re-find #"[0-9]+" s)))

(defn verify-ip [ip]
  (pos? (count (re-seq #"\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}" ip))))

(defn fetch-city-state [ip]
  (let [good-ip (verify-ip ip)]
    (cond 
          (true? good-ip) "Good IP. TODO - Retrieve city-state."
           :else (hash-map :status 404 :headers "{\"Content-Type\" \"text/html; charset=utf-8\"}" 
                           :body "Invalid IP address")
          )))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/city-state/:ip" [ip] (fetch-city-state ip))
  (GET "/documentation" [] "<h1>Documentation</h1><p>None yet</p>")
  (GET "/health-check" [] "<h1>Health Check</h1><p>TBD</p>")
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
