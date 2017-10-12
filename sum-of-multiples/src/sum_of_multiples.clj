(ns sum-of-multiples)

(defn multiples-up-to [limit of]
  (loop [multiples [], multiple of]
    (if (>= multiple limit)
      multiples
      (recur (conj multiples multiple) (+ multiple of)))))

(defn sum-of-multiples [numbers limit]
  (reduce + (set (mapcat (partial multiples-up-to limit) numbers))))