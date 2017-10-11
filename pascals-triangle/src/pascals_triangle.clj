(ns pascals-triangle)

(defn next-row [row]
  (apply vector (map +' (conj row 0) (cons 0 row))))

(def triangle (iterate next-row [1]))

(defn row [n]
  (last (take n triangle)))