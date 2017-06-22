(ns geo-ip-api.test.handler
  (:use clojure.test
        ring.mock.request  
        geo-ip-api.handler
        geo-ip-api.data-source))

(deftest validate-available-routes
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))
  
  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404))))

  (testing "get city-state route exists"
    (let [response (app (request :get "/city-state/192.182.16.42"))]
      (is (= (:status response) 200))))
  
  (testing "documentation route exists"
    (let [response (app (request :get "/documentation"))]
      (is (= (:status response) 200))))
  
  (testing "health-check route exists"
    (let [response (app (request :get "/health-check"))]
      (is (= (:status response) 200))))
)

(deftest test-verify-ip-behavior
  ;;TODO - I would like to put out a message when someone doesnt provide an IP instead of returning a 404. I don't know how to do this right now.
  ;;(testing "gracefully handle missing ip"
  ;;  (let [response (app (request :get "/city-state/"))]
  ;;    (is (= (:status response) "There are no city-states for a null IP."))))
  (testing "there is a fetch-city-state fuction that takes a single parameter"
    (is (= "class java.lang.Boolean" (str (type (verify-ip "192.192.192.192"))))))
  (testing "should not accept IPs with only 1 octet"
      (is (= false (verify-ip "192"))))
  (testing "should not accept IPs with only 2 octet"
      (is (= false (verify-ip "192.193"))))
  (testing "should not accept IPs with only 3 octet"
      (is (= false (verify-ip "192.193.123"))))
  (testing "should accept IPs with 4 octets"
      (is (= true (verify-ip "192.193.123.32"))))
  ;;TODO - Add restrictions on specific domains like 10. and others.
)

(deftest verify-convert-ip-behavior
  (testing "returns an HTTP response status"
    (let [response (app (request :get "/city-state/192.192"))]
      (is (= true (contains? response :status)))))
  (testing "returns a 404 status on bad IP address"
    (let [response (app (request :get "/city-state/192.192.3"))]
      (is (= 404 (:status response)))))
  (testing "68.75.30.67 returns = city Columbus"
    (is (= "Columbus" (.getName (.getCity (get-location "68.75.30.67"))))))
)

