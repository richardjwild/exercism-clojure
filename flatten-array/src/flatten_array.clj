(ns flatten-array)

(defn flatten [input]
  (apply vector (remove nil? (clojure.core/flatten input))))