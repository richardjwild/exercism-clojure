(ns phone-number)

(defn get-valid [no]
  (let [cleaned-no (apply str (filter #(Character/isDigit %1) no))]
    (case (count cleaned-no)
      10 cleaned-no
      11 (when (= \1 (first cleaned-no)) (subs cleaned-no 1))
      nil)))

(defn number [no]
  (if-let [valid-number (get-valid no)]
    valid-number "0000000000"))

(defn area-code [no]
  (subs (get-valid no) 0 3))

(defn pretty-print [no]
  (let [valid-number (get-valid no)]
    (format "(%s) %s-%s"
            (subs valid-number 0 3)
            (subs valid-number 3 6)
            (subs valid-number 6))))
