(ns sum-of-multiples)

(defn multiples-up-to [limit of]
  (range of limit of))

(defn sum-of-multiples [numbers limit]
  (reduce + (set (mapcat (partial multiples-up-to limit) numbers))))