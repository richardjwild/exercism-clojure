(ns minesweeper
  (require [clojure.string :as str]))

(defn mine? [cell]
  (= \* cell))

(defn overlay-cell [top bottom]
  (if (mine? top) top bottom))

(defn overlay-boards [top bottom]
  (reduce str (map overlay-cell top bottom)))

(defn cell-as-text [cell-value]
  (if (zero? cell-value) \space (str cell-value)))

(defn sum-up [& vals]
  (reduce + vals))

(defn generate-cell [neighbours y x]
  (if (contains? neighbours [x y]) 1 0))

(defn generate-line [neighbours width y]
  (map (partial generate-cell neighbours y)
       (range 0 width)))

(defn generate-board [dimensions neighbours]
  (mapcat (partial generate-line neighbours (dimensions :w))
          (range 0 (dimensions :h))))

(def neighbours
  [[-1, 1] [0, 1] [1, 1]
   [-1, 0] [1, 0]
   [-1, -1] [0, -1] [1, -1]])

(defn neighbours-of [x y]
  (set (map (fn [[x-offs y-offs]] [(+ x-offs x) (+ y-offs y)]) neighbours)))

(defn board-for-cell [dimensions y x cell]
  (generate-board dimensions (if (mine? cell) (neighbours-of x y))))

(defn boards-for-line [dimensions line y]
  (map (partial board-for-cell dimensions y)
       (range 0 (dimensions :w))
       line))

(defn draw [input-board]
  (let [lines (str/split-lines input-board),
        dimensions {:h (count lines), :w (count (first lines))}]
    (->> (mapcat (partial boards-for-line dimensions)
                 lines
                 (range 0 (dimensions :h)))
         (apply map sum-up)
         (map cell-as-text)
         (partition (dimensions :w))
         (map (partial reduce str))
         (interpose \newline)
         (reduce str)
         (overlay-boards input-board))))
