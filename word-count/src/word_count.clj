(ns word-count
  (require [clojure.string :as str]))

(defn number-of-times-word-seen
  [word word-map]
  (if-let [n (get word-map word)]
    (inc n) 1))

(defn is-alphanumeric [c]
  (or (Character/isLetter c) (Character/isDigit c)))

(defn filter-and-normalize-characters [input]
  (let [filtered (apply str (filter (fn [c] (is-alphanumeric c)) input))]
    (if (= "" filtered) nil (str/lower-case filtered))))

(defn count-distinct-words [word-list]
  (if-let [word (first word-list)]
    (let [words-counted-so-far (count-distinct-words (rest word-list))]
      (if-let [filtered-word (filter-and-normalize-characters word)]
        (assoc words-counted-so-far filtered-word (number-of-times-word-seen filtered-word words-counted-so-far))
        words-counted-so-far))
    {}))                                                      ; it all begins with an empty map

(defn word-count [input-string]
  (count-distinct-words (str/split input-string #" ")))
