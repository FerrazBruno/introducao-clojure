(ns introducao-clojure.core-test
  (:require [clojure.test :refer :all]
            [introducao-clojure.core :refer :all]))

(deftest fizzbuzz-test
  (testing "Verificando numeros individuais"
    (is (= (fizzbuzz 15) "FizzBuzz"))
    (is (= (fizzbuzz 3) "Fizz"))
    (is (= (fizzbuzz 5) "Buzz"))
    (is (= (fizzbuzz 7) 7))))


