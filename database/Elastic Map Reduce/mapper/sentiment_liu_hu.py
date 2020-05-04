#!/usr/bin/python

import cPickle as pickle
from nltk.classify import NaiveBayesClassifier
from nltk.tokenize import word_tokenize, treebank
from nltk.sentiment.util import demo_liu_hu_lexicon
from nltk.corpus import opinion_lexicon
import sys

sys.stderr.write("Started mapper 5524.\n");

def subj(subjLine):
    subjgen = subjLine.lower()
    # Replace term1 with your subject term
    subj1 = "trump"
    if subjgen.find(subj1) != -1:
        subject = subj1
        return subject
    else:
        subject = "No match"
        return subject
	
def acisWordAnalysis(sentence):	
    tokenizer = treebank.TreebankWordTokenizer()
    pos_words = 0
    neg_words = 0
    tokenized_sent = [word.lower() for word in tokenizer.tokenize(sentence)]	
    for word in tokenized_sent:
        if word in opinion_lexicon.positive():
            pos_words += 1
        elif word in opinion_lexicon.negative():
            neg_words += 1

    if pos_words > neg_words:
        return "Positive"
    elif pos_words < neg_words:
        return "Negative"
    elif pos_words == neg_words:
        return "Neutral"

def main(argv):
	for line in sys.stdin:
		subjectFull = subj(line)
		if subjectFull == "No match":
			print "LongValueSum:" + "No match" + "\t" + "1"
		else:
			print "LongValueSum:" + acisWordAnalysis(line) + "\t" + "1"			
			
if __name__ == "__main__":
    main(sys.argv)
