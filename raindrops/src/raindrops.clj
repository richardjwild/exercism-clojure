(ns raindrops)

(def raindrops {3 "Pling", 5 "Plang", 7 "Plong"})

(defn divisible-by? [dividend divisor]
  (zero? (rem dividend divisor)))

(defn make-fn [[divisor raindrop]]
  (fn [n] (when (divisible-by? n divisor) raindrop)))

(defn convert [n]
  (let [raindrops ((apply juxt (map make-fn raindrops)) n)]
    (if (every? nil? raindrops)
      (str n)
      (apply str raindrops))))