import './gameHistory.scss';
import React, {useState} from 'react';

export const GameHistory = ({ gameHistory }) => {
    const [tableOpen, setTableOpen] = useState(false);
    const { history } = gameHistory;

    let gameTable = history;
    if (!tableOpen && history.length) {
        gameTable = [history[history.length - 1]];
    }

    const classes = `game-history ${tableOpen ? 'game-history_open' : null}`;

    return <div className={classes}>
        <table className='game-history__table'>
            <thead>
                <tr>
                    <th>Player</th>
                    <th>Skynet</th>
                    <th>Result</th>
                </tr>
            </thead>
            <tbody>
                {gameTable.map((it, index) => <tr key={index}>
                    <td>{it.playerChoice}</td>
                    <td>{it.skynetChoice}</td>
                    <td>{it.outcome}</td>
                </tr>
                )}
            </tbody>
        </table>
        <button className='game-history__table-open' onClick={() => setTableOpen((tableOpen) => !tableOpen)}/>
    </div>
};
