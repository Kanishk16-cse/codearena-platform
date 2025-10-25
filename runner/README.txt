Build runner image: docker build -t codearena-runner:latest ./runner
Runner expects to find solution.py mounted at /workspace and will be invoked as: python /workspace/solution.py