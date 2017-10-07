(ns perfect-numbers)

(defn is-factor-of [m n]
  (zero? (rem m n)))

(defn factors-of [n]
  (filter (partial is-factor-of n) (range 1 n)))

(defn sum-of-factors [n]
  (reduce + (factors-of n)))

(defn classify [n]
  (if (neg? n)
    (throw (IllegalArgumentException. "no negative numbers please!"))
    (condp apply [n (sum-of-factors n)]
      > :deficient
      < :abundant
      = :perfect)))