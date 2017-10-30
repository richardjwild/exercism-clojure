(ns accumulate)

(defn accumulate [f coll]
  (loop [[first & rest] coll, result []]
    (if (nil? first)
      result
      (recur rest (conj result (f first))))))