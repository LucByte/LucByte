# pandas to modify dataset, numpy for scientific ops, matplot for graph
# sklearn to scale data, keras to work as wrapper for tf neural network library
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.preprocessing import MinMaxScaler
from keras.models import Sequential, load_model
from keras.layers import LSTM, Dense, Dropout

# data loaded by putting csv file in code folder
# reading data
df = pd.read_csv('UCL_bike_rental.csv')

# df.shape represents dimensionality of dataframe
df = df['cnt'].values
df = df.reshape(-1,1)

#data is split into training set (80% of data)
#and test set (20% of data)
dataset_train = np.array(df[:int(df.shape[0]*0.8)])
dataset_test = np.array(df[int(df.shape[0]*0.8):])
# Scale data to range 0,1
scaler = MinMaxScaler(feature_range=(0,1))
dataset_train = scaler.fit_transform(dataset_train)
dataset_test = scaler.transform(dataset_test)

#Create input-output pairs to train supervised learning model
def create_dataset(df):
    x=[]
    y=[]
    for i in range(50, df.shape[0]):
        x.append(df[i-50:i, 0])
        y.append(df[i,0])
    x = np.array(x)
    y = np.array(y)
    return x,y
x_train, y_train = create_dataset(dataset_train)
x_test, y_test = create_dataset(dataset_test)
# shaping data to 3D array to use LSTM layer
x_train = np.reshape(x_train, (x_train.shape[0], x_train.shape[1], 1))
x_test = np.reshape(x_test, (x_test.shape[0], x_test.shape[1], 1))

#Consists of 4 LSTM layers
model = Sequential()
model.add(LSTM(units=96, return_sequences=True, input_shape=(x_train.shape[1], 1)))
model.add(Dropout(0.2))
model.add(LSTM(units=96,return_sequences=True))
model.add(Dropout(0.2))
model.add(LSTM(units=96,return_sequences=True))
model.add(Dropout(0.2))
model.add(LSTM(units=96))
model.add(Dropout(0.2))
model.add(Dense(units=1))

x_train = np.reshape(x_train, (x_train.shape[0], x_train.shape[1], 1))
x_test = np.reshape(x_test, (x_test.shape[0], x_test.shape[1], 1))

model.compile(loss='mean_squared_error', optimizer='adam')

model.fit(x_train, y_train, epochs=50, batch_size=32)
# training examples per iteration)
model.save('bike_demand_prediction.h5')
#graphing
model = load_model('bike_demand_prediction.h5')
predictions = model.predict(x_test)
predictions = scaler.inverse_transform(predictions)
y_test_scaled = scaler.inverse_transform(y_test.reshape(-1, 1))
fig, ax = plt.subplots(figsize=(16,8))
ax.set_facecolor('#000041')
ax.plot(y_test_scaled, color='red', label='Actual demand')
plt.plot(predictions, color='green', label='Predicted demand')
plt.legend()

