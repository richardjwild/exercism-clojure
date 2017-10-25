(ns queen-attack)

(def zero-to-seven (take 8 (range)))

(defn- append-newline [s]
  (str s "\n"))

(defn- cell-state [occupied-positions row column]
  (condp = [row column]
    (occupied-positions :w) "W"
    (occupied-positions :b) "B"
    "_"))

(defn row-string [occupied-positions row]
  (->> (map (partial cell-state occupied-positions row) zero-to-seven)
       (interpose " ")
       (reduce str)
       append-newline))

(defn board-string [occupied-positions]
  (reduce str (map (partial row-string occupied-positions) zero-to-seven)))

(defn- any-true? [coll]
  (not (empty? (filter true? coll))))

(defn- distance [a b]
  (Math/abs (- a b)))

(defn- horizontal? [[x1 _] [x2 _]]
  (= x1 x2))

(defn- vertical? [[_ y1] [_ y2]]
  (= y1 y2))

(defn- diagonal? [[x1 y1] [x2 y2]]
  (= (distance x1 x2) (distance y1 y2)))

(defn can-attack [occupied-positions]
  (any-true? ((juxt horizontal? vertical? diagonal?)
               (occupied-positions :w)
               (occupied-positions :b))))
