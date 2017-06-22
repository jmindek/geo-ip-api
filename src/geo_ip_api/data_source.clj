(ns geo-ip-api.data-source
  (:import (com.maxmind.geoip2 DatabaseReader
             DatabaseReader$Builder)
           (java.net InetAddress)))

(def db (clojure.java.io/file "resources/data/GeoLite2-City.mmdb"))
;; was helpful to see the DatabaseReader class to determine that
;; Builder was a static inner class
;; and that the constructor requires one
;; recall
;;   Classname. arg1 arg2 ... argn -> to create new class
;;   .method arg1 ... argn -> to call method on instance
(def reader (.build (DatabaseReader$Builder. db)))

(defn get-location
  [ip]
  (let [ip-addr (InetAddress/getByName ip)]
    (.city reader ip-addr)))

