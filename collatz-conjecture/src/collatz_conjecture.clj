(ns collatz-conjecture)

(defn get-next [value]
  (if (even? value)
    (/ value 2)
    (+ (* value 3) 1)))

(defn collatz [n]
  (if (<= n 0)
    (throw (IllegalArgumentException. "Number must be greater than zero"))
    (loop [step 0, value n]
      (if (= 1 value) step (recur (inc step) (get-next value))))))
