(ns flatten-array)

(defn flatten-array [a]
  (if (not (sequential? a))
    (mapcat flatten-array a)
    (list a)))

(defn flatten [input]
  (apply vector (remove nil? (mapcat flatten-array input))))