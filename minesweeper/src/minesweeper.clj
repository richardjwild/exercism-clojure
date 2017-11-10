(ns minesweeper
  (require [clojure.string :as str]))

(defn mine? [cell]
  (= \* cell))

(defn overlay-cell [top bottom]
  (if (mine? top) top bottom))

(defn overlay [top bottom]
  (reduce str (map overlay-cell top bottom)))

(defn number-to-text [cell-value]
  (if (zero? cell-value) " " (str cell-value)))

(defn reduce-cells [& cells]
  (reduce + cells))

(defn above-left [x y] [(dec x), (inc y)])
(defn above [x y] [x, (inc y)])
(defn above-right [x y] [(inc x), (inc y)])
(defn left [x y] [(dec x), y])
(defn right [x y] [(inc x), y])
(defn below-left [x y] [(dec x), (dec y)])
(defn below [x y] [x, (dec y)])
(defn below-right [x y] [(inc x), (dec y)])

(defn neighbours-of [x y]
  ((juxt above-left above above-right left right below-left below below-right) x y))

(defn neighbouring-cells [neighbours y x]
  (if (contains? neighbours [x y]) 1 0))

(defn neighbouring-lines [neighbours width y]
  (map (partial neighbouring-cells neighbours y) (take width (range))))

(defn generate-neighbours [width height neighbouring-cells]
  (mapcat (partial neighbouring-lines neighbouring-cells width) (take height (range))))

(defn generate-neighbour-cells
  ([width height line y]
   (map (partial generate-neighbour-cells width height y) line (range)))
  ([width height y cell x]
   (if (mine? cell)
     (generate-neighbours width height (set (neighbours-of x y)))
     (generate-neighbours width height #{}))))

(defn draw [board-string]
  (case board-string
    "" ""
    (let [board-lines (str/split-lines board-string),
          height (count board-lines),
          width (count (first board-lines))]
      (->> (mapcat (partial generate-neighbour-cells width height) board-lines (range))
           (apply map reduce-cells)
           (map number-to-text)
           (partition width)
           (map (partial reduce str))
           (interpose \newline)
           (reduce str)
           (overlay board-string)))))