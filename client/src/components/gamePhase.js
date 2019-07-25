import React, { useState, useEffect } from 'react';
import { GameForm } from './gameForm';
import { RivalForm } from './rivalForm';
import { gameService } from '../services/game.service';
import { GameResult } from './gameResult';
import { GameHistory } from './gameHistory';
import paper from '../resources/img/paper.jpg';
import rock from '../resources/img/rock.jpg';
import scissor from '../resources/img/scissor.jpg';

const GAME_INTERVAL = 2222;
const buttons = [{
    value: 'P',
    img: paper,
    alt: 'paper'
},
{
    value: 'R',
    img: rock,
    alt: 'rock'
},
{
    value: 'S',
    img: scissor,
    alt: 'scissor'
}];

export const GamePhase = ({ gameHistory }) => {
    const [playersResult, setPlayersResult] = useState({});
    const [currentHistory, setCurrentHistory] = useState(gameHistory);

    const toBegin = () => {
        setTimeout(() => {
            setPlayersResult({})
        }, GAME_INTERVAL);
    }

    const makeTurn = (value) => {
        gameService.makeTurn(value)
            .then(response => {
                const { skynetChoice, playerChoice, outcome } = response.outcome;
                const { stats } = response;
                setPlayersResult(() => {
                    const result = {
                        skynetChoice,
                        playerChoice,
                        outcome,
                        stats: stats
                    }
                    mergeHistory(result);
                    return result;
                });
                toBegin();
            })
    };

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

    return <div className='game-phase'>
        <GameForm buttons={buttons} makeTurn={makeTurn} playerChoice={playersResult.playerChoice} />
        {playersResult.outcome ? <GameResult outcome={playersResult.outcome} /> : null}
        <RivalForm buttons={buttons} skynetChoice={playersResult.skynetChoice} />
        <GameHistory gameHistory={currentHistory} />
    </div>
}