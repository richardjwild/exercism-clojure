(ns triangle)

(def triangle-types
  {1 :equilateral
   2 :isosceles
   3 :scalene})

(defn not-a-triangle [sides]
  (let [[a' b' c'] (sort sides)]
    (>= c' (+ a' b'))))

(defn type [a b c]
  (let [sides (vector a b c)]
    (if (not-a-triangle sides)
      :illogical
      (get triangle-types (count (set sides))))))