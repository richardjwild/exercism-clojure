(ns roman-numerals
  (require [clojure.string :as str]))

(defn i [n]
  (reduce str (take n (repeat "I"))))

(def roman-numerals
  (list ["M" (i 1000)]
        ["CM" (i 900)]
        ["D" (i 500)]
        ["CD" (i 400)]
        ["C" (i 100)]
        ["XC" (i 90)]
        ["L" (i 50)]
        ["XL" (i 40)]
        ["X" (i 10)]
        ["IX" (i 9)]
        ["V" (i 5)]
        ["IV" (i 4)]))

(defn numerals [number]
  (loop [[[numeral replacing] & rest] roman-numerals, result (i number)]
    (if (nil? numeral) result (recur rest (str/replace result replacing numeral)))))
