// Groovy does not have a character literal at all, so one-character strings have to be coerced to char.
// Groovy printf (like Java, but unlike C) is not type-agnostic, so the cast or coercion from char to int
// is also required. The reverse direction is considerably simpler.

printf ("%d\n", ('a' as char) as int)
printf ("%c\n", 97)