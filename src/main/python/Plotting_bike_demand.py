import matplotlib.pyplot as plt
from keras.saving.save import load_model
from bike_demand_forecasting import scaler, x_test, y_test

model = load_model('bike_demand_prediction.h5')
predictions = model.predict(x_test)
predictions = scaler.inverse_transform(predictions)
y_test_scaled = scaler.inverse_transform(y_test.reshape(-1, 1))
fig, ax = plt.subplots(figsize=(16,8))
ax.set_facecolor('#000041')
ax.plot(y_test_scaled, color='red', label='Actual demand')
plt.plot(predictions, color='green', label='Predicted demand')
plt.legend()
plt.show()
