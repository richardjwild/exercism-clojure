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

(defn generate-cell [neighbours y x]
  (if (contains? neighbours [x y]) 1 0))

(defn generate-line [neighbours width y]
  (map (partial generate-cell neighbours y) (take width (range))))

(defn generate-board [width height neighbouring-cells]
  (mapcat (partial generate-line neighbouring-cells width) (take height (range))))

(def neighbours-of
  (juxt (fn [x y] [(dec x), (inc y)])
        (fn [x y] [x, (inc y)])
        (fn [x y] [(inc x), (inc y)])
        (fn [x y] [(dec x), y])
        (fn [x y] [(inc x), y])
        (fn [x y] [(dec x), (dec y)])
        (fn [x y] [x, (dec y)])
        (fn [x y] [(inc x), (dec y)])))

(defn board-of-neighbours-for-cell
  ([width height line y]
   (map (partial board-of-neighbours-for-cell width height y) line (range)))
  ([width height y cell x]
   (if (mine? cell)
     (generate-board width height (set (neighbours-of x y)))
     (generate-board width height #{}))))

(defn draw [board-string]
  (case board-string
    "" ""
    (let [board-lines (str/split-lines board-string),
          height (count board-lines),
          width (count (first board-lines))]
      (->> (mapcat (partial board-of-neighbours-for-cell width height) board-lines (range))
           (apply map reduce-cells)
           (map number-to-text)
           (partition width)
           (map (partial reduce str))
           (interpose \newline)
           (reduce str)
           (overlay board-string)))))