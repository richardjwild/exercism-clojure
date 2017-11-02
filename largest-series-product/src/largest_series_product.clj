(ns largest-series-product)

(defn digit? [c]
  (Character/isDigit (char c)))

(defn valid-arguments? [series-length coll]
  (and (>= series-length 0)
       (<= series-length (count coll))
       (every? digit? coll)))

(defn char-to-int [c]
  (- (int c) (int \0)))

(defn chars-to-ints [coll]
  (map char-to-int coll))

(defn multiply-together [coll]
  (reduce * (chars-to-ints coll)))

(defn or-1 [x]
  (or x 1))

(defn largest-product [series-length coll]
  (if (not (valid-arguments? series-length coll))
    (throw (Exception. "WTF man this is shit"))
    (->> (partition series-length 1 coll)
         (map multiply-together)
         sort
         last
         or-1)))
