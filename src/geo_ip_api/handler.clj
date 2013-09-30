(ns geo-ip-api.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))
(defn verify-ip [ip]
  (pos? (count (re-seq #"\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}" ip))))

(defn fetch-city-state [ip]
  (verify-ip ip))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/city-state/:ip" [ip] (str (fetch-city-state ip)))
  (GET "/documentation" [] "<h1>Documentation</h1><p>None yet</p>")
  (GET "/health-check" [] "<h1>Health Check</h1><p>TBD</p>")
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))


