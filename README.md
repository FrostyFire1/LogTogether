# LogTogether - Because the default logs alone are not enough.
### Provides highly configurable logging of a variety of server activities.
#### GT5-UNOFFICIAL
- Right Click harvesting with Vajra
- LSC Storage monitoring
- Tile Entity Lag Spikes
- MultiBlock crashes

### That and more to come!

## Grafana integration
The mod comes with a .json file containing a grafana dashboard and a docker compose file for instant grafana setup.
### Setup
1. Install Docker.
2. Open the terminal in the Docker directory
3. Execute `docker compose up -d` - this should activate the necessary containers to send logs to grafana and grafana itself.
4. Check that grafana works by going to http://localhost:3000
5. Go to `Dashboard` and import the .json
6. Enjoy the logs
