# -*- coding: utf-8 -*-
"""
Created on Mon May 11 20:00:18 2020

@author: TOSHIBA
"""

import sys
import numpy as np
import pandas as pd
from datetime import date, timedelta
import seaborn as sns

import matplotlib.pyplot as plt



dataset = pd.read_csv('testfile.csv')
dataset['date'] = pd.to_datetime(dataset['date'])


#ARCHITECTS COMMITS
dataset = dataset.iloc[:,[0,1,3]]

dataset = dataset.drop_duplicates()
dataset = dataset.drop(['file'],axis=1)
dataset = dataset.groupby(['developer','date']).size().reset_index(name='files')



dataset.sort_values(by=[ 'developer','date'],inplace =True)



for i in range(1,len(dataset)):
    if dataset.developer.iloc[i-1] == dataset.developer.iloc[i]:
        dataset.files.iloc[i] = dataset.files.iloc[i] + dataset.files.iloc[i-1]

        
    
plt.figure(figsize=(20,9))
sns.lineplot(x="date", y="files", hue="developer",  data=dataset,
             palette='bright')
plt.savefig('scripts/img/graph_dev_time_files2.png')
