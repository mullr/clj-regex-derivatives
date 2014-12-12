# clj-regex-derivatives

A simple, very terse, implementation of regular expression
derivates. It is an exercise in brevity, and as such doesn't come with
very many useful features. But it's only 38 lines, including whitespace!

## Usage

```(matches "abbbbbc" [\a \b (->Many \b) \c])```

Regular clojure data structures are used as parser combinators.

- A character is the regex which accepts itself

- Seqs are used for sequence: ```[\a \b \c]``` matches "abc". If you just want
  to match a string, coerce it to a sequences with ```(seq "abc")```.

- Sets are used for disjunction: ```#{\a \b}``` matches "a" or "b"

- If you want Kleene *, use ```(->Many x)```

- If you want Kleene +, use ```[x (->Many x)]```

- if you want the question mark, use ```#{x (->Epsilon)}```


## License

Copyright © 2014 Russell Mull

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
