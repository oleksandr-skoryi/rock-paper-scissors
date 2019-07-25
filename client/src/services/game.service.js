const fetchDada = (url, data, method = 'get') => {
    const URL = 'http://rock-paper-scissors.eu-central-1.elasticbeanstalk.com/game';
    const token = localStorage.getItem('token') ? JSON.parse(localStorage.getItem('token')).token : {};
    const bodyData = {...data};
    let fetchOptions = {
        method: method,
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Game-Token': token,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyData)
    }
    if (method === 'get') {
        delete fetchOptions.body;
    }
    return fetch(`${URL}/${url}`, fetchOptions)
        .then(res => res.json());
};

class Game {
    gameDiscard() {
    }
    makeTurn(choice) {
        return fetchDada('makeTurn', choice, 'post');
    }
    gameStart() {
        return fetchDada('start', null, 'post')
            .then(res => localStorage.setItem('token', JSON.stringify(res)));
    }
    gameStats() {
        return fetchDada('stats');
    }
}

export const gameService = new Game();