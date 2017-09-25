(ns series
  (:require [clojure.string :as str]))

(defn get-slice-from [string length start]
  (let [end (+ start length)]
    (loop [pos start, slice ""]
      (if (>= pos end)
        slice
        (recur
          (inc pos)
          (str slice (subs string pos (inc pos))))))))

(defn slices [string n]
  (let [no-of-slices (inc (- (count string) n))]
    (loop [slices [], start-at 0]
      (if (>= start-at no-of-slices)
        slices
        (recur
          (conj slices (get-slice-from string n start-at))
          (inc start-at))))))
