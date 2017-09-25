(ns series
  (:require [clojure.string :as str]))

(defn slices [string n]
  (if (zero? n)
    [""]
    (loop [slices [], s string]
      (let [slice (reduce str (take n s))]
        (if (< (count slice) n)
          slices
          (recur (conj slices slice) (reduce str (drop 1 s))))))))

