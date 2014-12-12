(ns clj-regex-derivatives.core)

(defprotocol RegularLanguage
  (nullable? [this])
  (derivative [this c]))

(defrecord Epsilon []
  RegularLanguage
  (nullable? [this] true)
  (derivative [this c] #{}))

(extend-protocol RegularLanguage
  java.lang.Character
  (nullable? [this] false)
  (derivative [this c] (if (= this c) (->Epsilon) #{}))

  clojure.lang.Sequential
  (nullable? [this]
    (every? nullable? this))
  (derivative [this c]
    (let [[head & tail] this]
      (if (and (nullable? head) tail)
        #{(derivative tail c)
          (cons (derivative head c) tail)}
        (cons (derivative head c) tail))))

  clojure.lang.PersistentHashSet
  (nullable? [this] (boolean (some nullable? this)))
  (derivative [this c] (into #{} (map #(derivative % c) this))))

(defrecord Many [l]
  RegularLanguage
  (nullable? [this] true)
  (derivative [this c]
    [(derivative l c) this]))

(defn matches [input l]
  (nullable? (reduce derivative l input)))
