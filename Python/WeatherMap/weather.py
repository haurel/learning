import json, objectpath, requests, prettytable, datetime


def openJSON(name):
    with open(name, encoding='utf8') as fp:
        json_object = json.loads(fp.read())
        return json_object

def checkCityID(json_object, name):
    for dict in json_object:
        if dict['name'] == name:
            return dict['id']


if __name__ == "__main__":
    json_object = openJSON('city.json')
    city = input("Enter City: ")
    appID = ''
    print(checkCityID(json_object, city))
    cityID = checkCityID(json_object, city)

    url = 'https://api.openweathermap.org/data/2.5/forecast?id={}&appid={}&units=metric&lang=en'.format(cityID, appID)
    req = requests.get(url)
    data = req.json()
    table = prettytable.PrettyTable()
    table.field_names = ["Day", "Time", "Temperature", "Humidity", "Wind"]
    
    for i in range(40):
        temp = data["list"][i]["main"]["temp"]
        humidity = data["list"][i]["main"]["humidity"]
        wind = data["list"][i]["wind"]["speed"]
        time = data["list"][i]["dt_txt"]
        time = time.split(" ")
        table.add_row([datetime.datetime.strptime(time[0], '%Y-%m-%d').strftime('%d-%m-%Y'), time[1], temp, humidity, wind])

    print(table)
