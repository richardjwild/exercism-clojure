(ns octal)

(def base 8)

(defn to-int [digit]
  (- (int digit) (int \0)))

(defn valid-digit? [digit]
  (let [n (to-int digit)]
    (and (>= n 0) (< n base))))

(defn valid-number? [str]
  (empty? (remove valid-digit? str)))

(defn scale-then-add-digit [accumulator digit]
  (+ (* accumulator base) (to-int digit)))

(defn to-decimal [str]
  (if (valid-number? str) (reduce scale-then-add-digit 0 str) 0))
