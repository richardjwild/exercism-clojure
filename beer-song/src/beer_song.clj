(ns beer-song
  (require [clojure.string :as str]))

(defn plural [n] (str n " bottles"))

(defn bottles [n]
  (case n
    0 (plural "no more")
    1 "1 bottle"
    (plural n)))

(defn how-many-bottles-of-beer [n]
  (str (bottles n) " of beer"))

(defn how-many-bottles-of-beer-on-the-wall [n]
  (str (how-many-bottles-of-beer n) " on the wall"))

(defn how-many [n]
  (if (= n 1) "it" "one"))

(defn what-to-do [n]
  (if (= 0 n)
    (str "go to the store and buy some more, " (how-many-bottles-of-beer-on-the-wall 99))
    (str "take " (how-many n) " down and pass it around, " (how-many-bottles-of-beer-on-the-wall (dec n)))))

(defn line [s]
  (str (str/capitalize s) ".\n"))

(defn verse [n]
  (str
    (line (str (how-many-bottles-of-beer-on-the-wall n) ", " (how-many-bottles-of-beer n)))
    (line (what-to-do n))))

(defn sing
  ([verse-from verse-to]
   (if (= verse-from verse-to)
     (verse verse-from)
     (str (verse verse-from) "\n" (sing (dec verse-from) verse-to))))
  ([verse-from]
   (sing verse-from 0)))