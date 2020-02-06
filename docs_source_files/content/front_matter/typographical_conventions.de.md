---
title: "Typographische Richtlinien"
weight: 2
---

## Großschreibung bei Überschriften

Im Englischen ist es erwünscht in den Überschriften nur den
ersten Buchstaben groß zu schreiben und sich an die typografischen
Regeln zu halten. 
Sinngemäß soll dies auch für die deutsche Übersetzung gelten. 
Daher sollen in Überschriften Großbuchstaben nur dann verwendet werden
wenn es die deutsche Sprache auch vorsieht. Es kann verwirrend sein wenn 
_Eine Sehr Schöne Überschrift_ Großbuchstaben verwendet die in der 
Rechtschreibung nicht vorgesehen sind.

## Zeilenlänge

Wenn der Sourcecode, der in HTML geschrieben wurde,
verändert wird, achte bitte darauf das die Zeilenlänge
72 Zeichen nicht überschreitet.

Einige von uns gehen sogar einen Schritt weiter und halten sich an
[_semantic linefeeds_](//rhodesmill.org/brandon/2012/one-sentence-per-line)
(englische Quelle). Bei diesem Vorgehen wird im HTML Quellcode, welcher
nicht von der Öffentlichkeit gelesen wird, an 'natürlichen Pausen' ein
Zeilenumbruch eingefügt.
Anders formuliert, Sätze werden an natürlichen Pausen umgebrochen.
Statt sich mit den Zeilen der einzelnen Abschnitte zu befassen werden
diese Nahe des rechten Bildrandes enden, Zeilenumbrüche können 
jederzeit eingefügt werden sodass einzelne Inhalte getrennt werden.

Dadurch können diffs (Vergleiche zwischen zwei Dateien) sehr leicht 
gelesen und verglichen werden, vor allem dann wenn man git als 
Unterstützungwerkzeug verwendet. 

Wir bestehen jedoch nicht darauf das alle Mitwirkenden dies auch 
anwenden.

