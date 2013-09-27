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
    (let [response (app (request :get "/city-state"))]
      (is (= (:status response) 200))))
  
  (testing "documentation route exists"
    (let [response (app (request :get "/documentation"))]
      (is (= (:status response) 200))))
  
  (testing "health-check route exists"
    (let [response (app (request :get "/health-check"))]
      (is (= (:status response) 200))))
)

(deftest verify-city-state-behavior
  (testing "returns error message for bad IP address"
    (let [response (app (request :get "/city-state/"))])
)
