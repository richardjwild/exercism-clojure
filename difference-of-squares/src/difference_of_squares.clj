(ns difference-of-squares)

(defn- natural-numbers [n]
  (->> (range)
       (map inc)
       (take n)))

(defn- square [n]
  (* n n))

(defn square-of-sums [n]
  (square (reduce + (natural-numbers n))))

(defn sum-of-squares [n]
  (reduce + (map square (natural-numbers n))))

(defn difference [n]
  (- (square-of-sums n) (sum-of-squares n)))
