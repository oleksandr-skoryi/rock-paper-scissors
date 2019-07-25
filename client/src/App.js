import './App.scss';
import React, { useState, useEffect } from 'react';
import { gameService } from './services/game.service';
import { Button } from './components/button';
import { GamePhase } from './components/gamePhase';

function App() {
  const [gameResult, setGameResult] = useState('');
  const [gameHistory, setGameHistory] = useState({
    stats: {},
    history: []
  });
  const [gameStart, setGameStart] = useState(false);

  const startGame = () => {
    gameService.gameStart()
      .then(() => {
        gameService.gameStats()
          .then(response => {
            setGameHistory(response);
          });
      });
    setGameStart(gameStart => !gameStart);
  }

  return <div className='wrapper'>
    <div className='container game'>
      <div className='game__board'>
        {!gameStart ? <Button className='game__start' onClick={startGame}>Start Game</Button> : null}
        {gameStart ? <GamePhase gameHistory={gameHistory} /> : null}
      </div>
    </div>
  </div>;
}

export default App;
