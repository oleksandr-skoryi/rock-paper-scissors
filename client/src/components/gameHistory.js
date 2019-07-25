import './gameHistory.scss';
import React from 'react';

export const GameHistory = ({ gameHistory }) => {
    const { wins, loses, draws } = gameHistory.stats;

    return <div className='game-history'>
        <table className='game-history__table'>
            <thead>
                <tr>
                    <th>Player</th>
                    <th>Skynet</th>
                    <th>Result</th>
                </tr>
            </thead>
            <tbody>
                {gameHistory.history.map((it, index) => <tr key={index}>
                    <td>{it.playerChoice}</td>
                    <td>{it.skynetChoice}</td>
                    <td>{it.outcome}</td>
                </tr>
                )}
            </tbody>
        </table>
        <div className='game-history__results'>
            <p>Wins: {wins}</p>
            <p>Loses: {loses}</p>
            <p>Draws: {draws}</p>
        </div>
    </div>
}