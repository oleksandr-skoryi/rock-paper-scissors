import './gameFinalResult.scss';
import React from 'react';

export const GameFinalResult = ({stats}) => {
    const {wins, loses, draws} = stats;

    return <div className='game-final-result'>
        <div className="score">
            <p>Wins: {wins}</p>
        </div>
        <div className="score">
            <p>Loses: {loses}</p>
        </div>
        <div className="score">
            <p>Draws: {draws}</p>
        </div>
    </div>
};
