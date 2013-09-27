(ns geo-ip-api.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/city-state" [] "Columbus, OH (this is a canned response BTW!)")
  (GET "/documentation" [] "<h1>Documentation</h1><p>None yet</p>")
  (GET "/health-check" [] "<h1>Health Check</h1><p>TBD</p>")
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
