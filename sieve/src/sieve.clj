(ns sieve)

(defn ^:private non-primes-up-to [limit]
  (let [half-limit (-> (/ limit 2) Math/floor int)]
    (loop [n 2, non-primes '()]
      (if (> n half-limit)
        (set non-primes)
        (recur (inc n) (concat non-primes (range (* n 2) (inc limit) n)))))))

(defn sieve [limit]
  (let [non-primes (non-primes-up-to limit)]
    (remove #(contains? non-primes %) (range 2 (inc limit)))))