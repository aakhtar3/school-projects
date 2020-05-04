#!/bin/bash
sudo yum -y remove python
sudo yum -y install python27
sudo yum -y install git gcc
sudo ln -sf /usr/bin/python2.7 /usr/bin/python
sudo yum -y install python27-setuptools
sudo easy_install pip
sudo easy_install -U distribute
sudo pip install -U pyyaml nltk
sudo pip install -e git://github.com/mdp-toolkit/mdp-toolkit#egg=MDP
sudo python -m nltk.downloader -d /usr/share/nltk_data punkt
sudo python -m nltk.downloader -d /usr/share/nltk_data vader_lexicon
sudo python -m nltk.downloader -d /usr/share/nltk_data opinion_lexicon