# -*- coding: utf-8 -*-
"""
Created on Mon May  4 20:22:17 2020

@author: TOSHIBA
"""

# Importing the libraries

import pandas as pd

dataset = pd.read_csv('testfile.csv')


#NADJI KOLIKO FAJLOVA JE RADILO KOLIKO DEVELOPERA
file_devs = dataset.iloc[:,[0,3]]
file_devs = file_devs.drop_duplicates()

            

file_devs = file_devs.drop(['developer'],axis=1)
file_devs = file_devs.groupby(['file']).size().reset_index(name='devs')
file_devs = file_devs.drop(['file'],axis=1)
file_devs = file_devs.groupby(['devs']).size().reset_index(name='files')

print(file_devs)