(defproject geo-ip-api "0.1.0-SNAPSHOT"
  :description "Simple ip to geo web api."
  :url "http://github.com/jmindek/geo-ip-api.git"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [ring "1.2.0"]]
  :plugins [[lein-ring "0.8.5"],[lein-ring "0.8.7"]]
  :ring {:handler geo-ip-api.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}})
