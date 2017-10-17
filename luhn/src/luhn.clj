(ns luhn)

(defn- divisible-by-ten? [n]
  (zero? (rem n 10)))

(defn- adjust [product]
  (if (> product 9)
    (- product 9)
    product))

(defn- double-it [digit]
  (* 2 digit))

(defn- process-digits [[first second]]
  (if (nil? second)
    (list first)
    (list first (-> second
                    double-it
                    adjust))))

(defn- char-to-int [n]
  (- (int n) (int \0)))

(defn- process-pair [pair]
  (process-digits (map char-to-int pair)))

(defn- process-pairs [pairs]
  (map process-pair pairs))

(defn- sum-up [digits]
  (reduce + digits))

(defn- group-into-pairs [s]
  (partition-all 2 s))

(defn- digit? [c]
  (Character/isDigit c))

(defn- valid-numeric-string? [s]
  (and (> (count s) 1)
       (every? digit? s)))

(defn- luhn-valid? [s]
  (and (valid-numeric-string? s)
       (-> s
           reverse
           group-into-pairs
           process-pairs
           flatten
           sum-up
           divisible-by-ten?)))

(defn- space? [c]
  (= c \ ))

(defn- remove-spaces [s]
  (remove space? s))

(defn valid? [s]
  (-> s
      remove-spaces
      luhn-valid?))