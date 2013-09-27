(ns geo-ip-api.test.handler
  (:use clojure.test
        ring.mock.request  
        geo-ip-api.handler))

(deftest validate-available-routes
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))
  
  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404))))

  (testing "get city-state route exists"
    (let [response (app (request :get "/city-state/1"))]
      (is (= (:status response) 200))))
  
  (testing "documentation route exists"
    (let [response (app (request :get "/documentation"))]
      (is (= (:status response) 200))))
  
  (testing "health-check route exists"
    (let [response (app (request :get "/health-check"))]
      (is (= (:status response) 200))))
)

(deftest verify-convert-ip-behavior
  ;;TODO - I would like to put out a message when someone doesnt provide an IP instead of returning a 404. I don't know how to do this right now.
  ;;(testing "gracefully handle missing ip"
  ;;  (let [response (app (request :get "/city-state/"))]
  ;;    (is (= (:status response) "There are no city-states for a null IP."))))
  (testing "should not accept IPs with only 1 octet"
      (is (= (verify-ip "192") 0)))
;;  (testing "should not accept IPs with only 2 octet"
;;      (is (= (verify-ip "192.193") 0)))
;;  (testing "should not accept IPs with only 3 octet"
;;      (is (= (verify-ip "192.193.123") 0)))
)
