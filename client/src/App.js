import './App.scss';
import React, { useState } from 'react';
import { gameService } from './services/game.service';
import { Button } from './components/button';
import { GamePhase } from './components/gamePhase';
import { GameHistory } from './components/gameHistory';
import { GameFinalResult } from './components/gameFinalResult';

const GAME_INTERVAL = 2222;

function App() {
  const [gameHistory, setGameHistory] = useState({
    stats: {},
    history: []
  });
  const [gameStart, setGameStart] = useState(false);
  const [playersResult, setPlayersResult] = useState({});
  const [currentHistory, setCurrentHistory] = useState(gameHistory);

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

  const makeTurn = (value) => {
    gameService.makeTurn(value)
      .then(response => {
        const { skynetChoice, playerChoice, outcome } = response.outcome;
        const { stats } = response;
        const result = {
          skynetChoice,
          playerChoice,
          outcome,
          stats: stats
        };
        setPlayersResult(result);
        mergeHistory(result);
        toBegin();
      })
  };

  const toBegin = () => {
    setTimeout(() => {
      setPlayersResult({})
    }, GAME_INTERVAL);
  }

  const mergeHistory = (result) => {
    setCurrentHistory((currentHistory) => {
      const { history } = currentHistory;
      const { skynetChoice, playerChoice, outcome, stats } = result;
      const newHistory = [...history, { skynetChoice, playerChoice, outcome }];
      return {
        history: newHistory,
        stats
      }
    });
  };

  const stats = currentHistory.stats;

  return <div className='wrapper'>
    {gameStart ? <GameFinalResult stats={stats} /> : null}
    <div className='container game'>
      <div className='game__board'>
        {!gameStart ? <Button className='game__start' onClick={startGame}>Start Game</Button> : null}
        {gameStart ? <GamePhase makeTurn={makeTurn} currentHistory={currentHistory} playersResult={playersResult} /> : null}
      </div>
      <div className='game-phase__result'>
        {gameStart ? <GameHistory gameHistory={currentHistory} /> : null}
      </div>
    </div>
  </div>;
}

export default App;
