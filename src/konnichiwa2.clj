(ns konnichiwa2)


(def code [{"n" "ん"}
           {"wo" "を",
            "zo" "ぞ",
            "ku" "く",
            "ne" "ね",
            "mo" "も",
            "ro" "ろ",
            "pe" "ぺ",
            "ji" "じ",
            "nu" "ぬ",
            "ze" "ぜ",
            "po" "ぽ",
            "ya" "や",
            "zu" "ず",
            "bo" "ぼ",
            "ki" "き",
            "pu" "ぷ",
            "mi" "み",
            "bu" "ぶ",
            "sa" "さ",
            "bi" "び",
            "pi" "ぴ",
            "ma" "ま",
            "ni" "に",
            "wa" "わ",
            "go" "ご",
            "gu" "ぐ",
            "da" "だ",
            "de" "で",
            "ri" "り",
            "hi" "ひ",
            "so" "そ",
            "ru" "る",
            "ha" "は",
            "ga" "が",
            "be" "べ",
            "se" "せ",
            "ba" "ば",
            "ge" "げ",
            "do" "ど",
            "za" "ざ",
            "mu" "む",
            "ra" "ら",
            "ke" "け",
            "no" "の",
            "re" "れ",
            "ho" "ほ",
            "gi" "ぎ",
            "ko" "こ",
            "te" "て",
            "ka" "か",
            "su" "す",
            "na" "な",
            "to" "と",
            "pa" "ぱ",
            "fu" "ふ",
            "ta" "た",
            "he" "へ",
            "me" "め",
            "yu" "ゆ",
            "yo" "よ"}
           {"tsu" "つ",
            "chi" "ち",
            "shi" "し"}])

(defn n-head [word n]
      (subs word 0 n))

(defn n-rest [word n]
      (subs word n (count word)))

(defn size-cap [word cap]
      (let [size (count word)]
           (if (< size cap)
             size
             cap)))

(defn break-code [word n]
      (if (= n 0)
        ["" ""]
        (if-let [deco (get (nth code (- n 1)) (n-head word n))]
                [(n-rest word n) deco]
                (break-code word (- n 1)))))

(defn decode [word]
      (if (zero? (count word))
        ""
        (let [length (size-cap word 3)
              [rest deco] (break-code word length)]
             (str deco (decode rest)))))
