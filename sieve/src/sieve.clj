(ns sieve
  (require [clojure.set :as set]))

(defn- multiples-up-to [limit n]
  (range (* n 2) (inc limit) n))

(defn- half [limit]
  (-> (/ limit 2)
      Math/ceil
      int))

(defn- non-primes-up-to [limit]
  (-> (mapcat (partial multiples-up-to limit) (range 2 (half limit)))
      set))

(defn- numbers-up-to [limit]
  (-> (range 2 (inc limit))
      set))

(defn sieve [limit]
  (-> (set/difference (numbers-up-to limit) (non-primes-up-to limit))
      sort))