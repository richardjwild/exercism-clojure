(ns robot-name)

(def used-names (atom #{}))

(defn random-digit []
  (rand-int 10))

(defn random-character []
  (char (+ 65 (rand-int 25))))

(defn random-name []
  (format "%c%c%d%d%d"
          (random-character)
          (random-character)
          (random-digit)
          (random-digit)
          (random-digit)))

(defn generate-name []
  (loop []
    (let [name (random-name)]
      (if (not (contains? @used-names name))
        name
        (recur)))))

(defn robot []
  (let [new-name (generate-name)]
    (reset! used-names (conj @used-names new-name))
    (atom new-name)))

(defn reset-name [a-robot]
  (reset! a-robot @(robot)))

(defn robot-name [a-robot]
  @a-robot)
